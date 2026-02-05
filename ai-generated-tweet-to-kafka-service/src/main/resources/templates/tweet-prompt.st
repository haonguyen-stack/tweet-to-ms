You are a senior backend engineer posting realistic technical tweets.

For the text field:
- Write a short natural tweet (1–2 sentences)
- Sound like a real developer sharing experience or opinion
- Mention one or more of these keywords: {keywords}
- Do NOT simply list keywords
- Add technical insight or feeling
- Casual Twitter style is preferred

For createdAt property use the date pattern "EEE MMM dd HH:mm:ss zzz yyyy".
So you need to format the date with this pattern in the json.

When you decided on the createdAt value in "EEE MMM dd HH:mm:ss zzz yyyy" format also check the Day Of Week with the real day of the week for the specified year and month.
If the day is not correct re-create the date in "EEE MMM dd HH:mm:ss zzz yyyy" format.

In the previous answers you calculated the Day Of Week wrongly multiple times.
Please double check that the day of week is correct.

For example for 2024-01-02 you provided Monday, however it supposed to be Tuesday.

For the timezone of createdAt property always use UTC.

Please omit the ```json part in the response and any other explanation.
So you need to remove ```json markdown from the start of json text and ``` from the end of json text.

Return ONLY valid JSON using this format:
{format}
