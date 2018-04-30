tccApp.filter('percentage', ['$window', function ($window) {
        return function (input) {
            var transformedInput = input.toFixed(2);
            transformedInput = transformedInput+'';
            if (input){
                transformedInput = transformedInput.replace('.', ','); 
                transformedInput = transformedInput + '%';
            }
            return transformedInput
        };
    }]);