<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Create</title>
        <script src="../js/script.js"></script>

        <link rel="stylesheet" href = "../css/style.css">
    </head>
    <body>
        <div class="form-group row">
            <textarea class="code_snippet" id="code_snippet" placeholder="Please write/paste your code here" name="create" required></textarea>
        </div>
        <div class="form-group row">
            <label for="time_restriction">Time Restriction (in seconds):</label>
            <div>
                <input type="text" class="time_restriction" id="time_restriction" placeholder="Time" value="0" required>
            </div>
        </div>
        <div class="form-group row">
            <label for="views_restriction">Views Restriction:</label>
            <div>
                <input type="text" class="views_restriction" id="views_restriction" placeholder="Views" value="0" required>
            </div>
        </div>

        <button id="send_snippet" type="submit" class="send_snippet" onclick="send()">Submit</button>
        <div id="result"></div>
    </body>
</html>