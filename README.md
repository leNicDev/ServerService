# ServerService
ServerService is a RESTful API that provides information about your Minecraft server.

How is it working?
-
ServerService starts an embedded HTTP server which provides
various methods to monitor and control your Minecraft server.
To prevent unauthorized users from accessing the API, ServerService provides
a configurable role-based permission system. A role has a name, a token and 
some permissions. To make a valid request, the "Authorization" header has to be set
to the role's token. Now you can access all the API functions that your role has the
permission for.
