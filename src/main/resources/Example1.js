var functionNames = function() {
  return true;
};

var variableNames = 'variable';

var arrays = [1, 2, 3];

var objects = {
  top: 10,
  right: 20,
  bottom: 15,
  left: 12
};

var stringsPreferSingleQuotes = 'over double quotes';

function functionDeclaration() {
  return true;
}

var functionExpression = function() {
  return true;
};

if (functionDeclaration()) {
  var inBlockFunctionsMustBeExpressions = function() {
    return false;
  };
} else {
  console.log('Curly braces on same line as whatever they are opening');
}
