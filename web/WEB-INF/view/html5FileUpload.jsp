<%--
  Created by IntelliJ IDEA.
  User: lujin
  Date: 2019/9/1
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Upload</title>
</head>
<body>
<h1>
    Multiple file uploads with progress bar
</h1>
<div id="progressBar" style="height: 20px; border: 2px solid green;">
    <div id="bar" style="height: 100%; background-color: #33dd33; width: 0"></div>
</div>
<form>
    <input type="file" id="files" multiple/>
    <br/>
    <output id="selectedFiles"></output>
    <input id="uploadButton" type="button" value="Upload"/>
</form>
<%--div 不可以自闭和--%>
<div id="debug" style="height: 100px; border: 2px solid green; overflow: auto;"></div>
</body>
<script>
    let totalFileLength, totalUploaded, fileCount, filesUploaded;

    function debug(s) {
        let debug = document.getElementById('debug');
        if (debug) {
            debug.innerHTML = debug.innerHTML + '<br/>' + s;
        }
    }

    function onUploadComplete(e) {
        totalUploaded += document.getElementById('files').files[filesUploaded].size;
        filesUploaded++;
        debug('complete ' + filesUploaded + ' of ' + fileCount);
        debug('totalUploaded: ' + totalUploaded);
        if (filesUploaded < fileCount) {
            uploadNext();
        } else {
            let bar = document.getElementById('bar');
            bar.style.width = '100%';
            bar.innerHTML = '100% complete';
            alert('Finished uploading file(s).');
        }
    }

    function onFileSelect(e) {
        let files = e.target.files;
        let output = [];
        fileCount = files.length;
        totalFileLength = 0;
        for (let i = 0; i < fileCount; i++) {
            let file = files[i];
            output.push(file.name, ' (', file.size, ' bytes, ', file.lastModifiedDate.toLocaleDateString(), ')');
            output.push('<br/>');
            debug('add ' + file.size);
            totalFileLength += file.size;
        }
        document.getElementById('selectedFiles').innerHTML = output.join('');
        debug('totalFileLength: ' + totalFileLength);
    }

    function onUploadProgress(e) {
        if (e.lengthComputable) {
            let percentComplete = parseInt((e.loaded + totalUploaded) * 100 / totalFileLength);
            let bar = document.getElementById('bar');
            bar.style.width = percentComplete + '%';
            bar.innerHTML = percentComplete + '% complete';
        } else {
            debug('unable to compute');
        }
    }

    function onUploadFailed(e) {
        alert('Error uploading file!');
    }

    function uploadNext() {
        let xhr = new XMLHttpRequest();
        let fd = new FormData();
        let file = document.getElementById('files').files[filesUploaded];
        fd.append("multipartFile", file);
        // 数据传输进行中
        xhr.upload.addEventListener('progress', onUploadProgress, false);
        // XMLHttpRequest 请求成功完成时触发, 也可以使用 onload 属性
        xhr.addEventListener('load', onUploadComplete, false);
        // 当 request 遭遇错误时触发, 也可以使用 onerror 属性
        xhr.addEventListener('error', onUploadFailed, false);
        // 初始化一个请求
        xhr.open('POST', 'file_upload');
        debug('uploading ' + file.name);
        // 方法用于发送 HTTP 请求。如果是异步请求（默认为异步请求），则此方法会在请求发送后立即返回；如果是同步请求，则此方法直到响应到达后才会返回。
        xhr.send(fd);
    }

    function startUpload() {
        totalUploaded = filesUploaded = 0;
        uploadNext();
    }

    window.onload = () => {
        document.getElementById('files').addEventListener('change', onFileSelect, false);
        document.getElementById('uploadButton').addEventListener('click', startUpload, false);
    }
</script>
</html>
