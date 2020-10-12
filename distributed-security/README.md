1. get token (depend on grant_type)
    - grant_type == authorization_code
        1. http://localhost:xxxxx/xxx/oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=http://www.google.com
            - GET grant code, then POST to get token
        2. http://localhost:xxxxx/uaa/oauth/token
             - POST
                 - grant_type
                 - client_id
                 - client_secret
                 - redirect_uri
                 - ...
         3. get the token
                 
    - else grant_type (client_credentials, password, implicit, refresh_token)
        1. http://localhost:xxxxx/uaa/oauth/token
            - POST
                - grant_type
                - client_id
                - client_secret
                - redirect_uri
                - ...
        2. get the token

2. get resource
    - http://localhost:12346/order/r1