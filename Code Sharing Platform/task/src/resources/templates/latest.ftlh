<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Latest</title>

        <link rel="stylesheet"
               target="_blank" target="_blank" target="_blank" target="_blank" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>

        <link rel="stylesheet" href="../css/style.css">
    </head>
    <body>
        <#list codes as code>
            <span class="load_date">${code.getFormattedCreateTime()}</span>
            <pre>
                <code class="code_snippet">${code.getCode()}</code>
            </pre>
        </#list>
    </body>
</html>