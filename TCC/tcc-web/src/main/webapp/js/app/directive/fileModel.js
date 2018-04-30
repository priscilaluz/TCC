tccApp.directive('fileModel', function ($parse) {
    return {
        require: '^ngModel',
        link: function (scope, element, attrs, modelCtrl) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            modelCtrl.$setValidity(element[0].name, false);

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                    if (element[0].files[0]) {
                        modelCtrl.$setValidity(element[0].name, true);
                    } else {
                        modelCtrl.$setValidity(element[0].name, false);
                    }
                });
            });
        }
    };
});