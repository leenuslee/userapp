<!DOCTYPE html>

<html lang="en" ng-app="userApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/UserService.js"></script>
        <script src="js/app/UserController.js"></script>
    </head>
    <body>
        <div ui-view></div>
    </body>
</html>