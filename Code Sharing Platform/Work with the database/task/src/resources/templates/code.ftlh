<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Code</title>
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet"
               target="_blank" target="_blank" target="_blank" target="_blank" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
    <head>
    <body>
        <span id="load_date" class="load_date">${code.getFormattedCreateTime()}</span>
        <pre id="code_snippet">
            <code class="code_snippet">${code.getCode()}</code>
        </pre>
    </body>
</html>