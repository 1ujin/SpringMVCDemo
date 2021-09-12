package com.mvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PreDestroy;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.concurrent.Callable;

// RestController = Controller + ResponseBody
@Controller
public class NonBlockingAsyncHelloServlet {
    // 定义一个静态线程池，若是采用asyncContext.start()方法则不用定义
    // private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 100000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

    @RequestMapping(value = "/async", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @RequestMapping(value = "/async", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收request和response!");
        int time = Integer.parseInt(request.getParameter("time")); // 获取延迟时间
        System.out.println("延迟时间: " + time + "毫秒!");
        response.setContentType("text/html;charset=UTF-8");

        // 开始异步处理，释放请求线程
        AsyncContext asyncContext = request.startAsync();
        // 可选设置超时时长，到了时间以后，会回调监听器中的onTimeout的方法
        asyncContext.setTimeout(5000);
        // 可选加入监听器
        asyncContext.addListener(new NonBlockingAsyncListener());
        System.out.println(Thread.currentThread() + "生成一个带有监听器的AsyncContext!");

        // lambda推导一个匿名Runnable类的实例
        // 两种线程方法的区别：http://www.iteye.com/problems/101332
        // executor.execute(() -> { // 线程池方法
        asyncContext.start(() -> { // 异步上下文方法
            // 定义run()方法
            try {
                System.out.println(Thread.currentThread() + "开始休眠!");
                Thread.sleep(time); // 根据时间休眠
                System.out.println(Thread.currentThread() + "结束休眠!");
                response.getWriter().println("response输出流: " + Thread.currentThread().toString()); // 通过异步上下文进行对response的操作
                // 以上做耗时的操作，如果做完，则调用complete方法通知回调，异步处理结束了
                asyncContext.complete(); // 结束此异步上下文线程
                System.out.println(Thread.currentThread() + "的AsyncContext结束!");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        response.getWriter().println("<p>此输出后发先至，等待异步输出流结果: </p>");
        response.getWriter().flush();
    }

    @PreDestroy
    public void destroy() {
        // 关闭线程池，否则需要stop两次，若是采用asyncContext.start()方法则不用关闭
        // executor.shutdown();
        System.out.println("线程池已关闭!");
    }

    @ResponseBody
    @RequestMapping(value = "call", method = RequestMethod.GET)
    protected Callable<String> call() {
        System.out.println("主线程开始..." + Thread.currentThread() + "==>" + Instant.now().getEpochSecond());
        Callable<String> callable = new Callable<>() {
            @Override
            public String call() throws Exception {
                System.out.println("副线程开始..." + Thread.currentThread() + "==>" + Instant.now().getEpochSecond());
                Thread.sleep(5000);
                System.out.println("副线程结束..." + Thread.currentThread() + "==>" + Instant.now().getEpochSecond());
                return "Callable<String> call()";
            }
        };
        System.out.println("主线程结束..." + Thread.currentThread() + "==>" + Instant.now().getEpochSecond());
        return callable;
    }
}

class NonBlockingAsyncListener implements AsyncListener {
    // servlet3.0原生的异步请求超时或者被唤醒之后不会像continuation那样重新模拟一次http请求。onComplete方法不论是在超时或者被唤醒均会被调用。
    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("工作处理完成");
        ServletResponse response = event.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.println("工作处理完成");
        out.flush();
        out.close();
    }

    // 当参数 time 大于 asyncContext 设定的超时时长则触发此方法
    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("工作超时");
        ServletResponse response = event.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.println("工作超时");
        out.flush();
        out.close();
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("工作处理错误");
        ServletResponse response = event.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.println("工作处理错误");
        out.flush();
        out.close();
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("工作处理开始");
        ServletResponse response = event.getAsyncContext().getResponse();
        PrintWriter out = response.getWriter();
        out.println("工作处理开始");
        out.flush();
        out.close();
    }
}
