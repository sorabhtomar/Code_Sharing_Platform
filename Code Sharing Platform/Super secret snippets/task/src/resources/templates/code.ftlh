<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Code</title>
        <link rel="stylesheet"
               target="_blank" target="_blank" target="_blank" target="_blank" target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>

        <link rel="stylesheet" href="../css/style.css">
    <head>
    <body>
        <span id="load_date" class="load_date">${code.getFormattedCreateTime()}</span>

        <#if code.isSecret() && code.getViews() gte 0>
            <span id="time_restriction" class="secret_code_info">
                <span class="highlight">${code.getViews()}</span> more views allowed
            </span>
        </#if>
        <#if code.isSecret() && code.getTime() gt 0>
            <span id="views_restriction" class="secret_code_info">
                The code will be available for <span class="highlight">${code.getTime()}</span> seconds
            </span>
        </#if>

        <pre id="code_snippet">
            <code class="code_snippet">${code.getCode()}</code>
        </pre>
    </body>
</html>