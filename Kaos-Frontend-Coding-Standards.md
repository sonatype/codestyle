# Sonatype Kaos Frontend Coding Standards

*Note: This guide applies only to the Kaos team which works on the Nexus Lifecycle products.*

At Sonatype we value the stability and maintainability of the code base while stressing the ability of the developers to quickly understand, consume and update the code base. We adopt code practices which facilitate our developers in rapidly creating new features without jeopardizing the existing feature set. Below are a set of code guidelines that we enforce in order to ensure that our values are upheld. The guidelines are short and written with consideration that different developers approach problems differently; they focus on practices that we have found optimize our ability to do our job without stifiling the process of development. This document is maintained in source control and therefore fluid, we encourage pull requests so that our code guidelines improve with our team.

## Technology Stack
* [Angular JS](https://angularjs.org)
  * We use AngularJS as a MVC framework for our frontend code
  * We utilize MVC as it provides a best practice to seperate the concerns of the data, the view and the business logic into separate chunks
  * We picked a framework that was all inclusive as to reduce the need to pick other frameworks and the churn required to maintain their updates
* [AngularUI Router](http://angular-ui.github.io/ui-router/site)
  * We use AngularUI Router as our routing framework
  * We chose AngularUI Router over AngularJS's internal routing as it provides states of MVC beyond a simple routing solution
* [Jasmine](http://jasmine.github.io)
  * We use Jasmine for unit testing
* [Selenide](http://selenide.org)
  * We use Selenide for functional testing

## Core Development
* We prefer native functions over library functions
  * While we have a preferred technological stack, we desire portable code that can be understood by any developer. As such we prefer the use of native functions over those provided by a framework
  * Likewise, we prefer utilizing our core stack over other libraries
  * As an example, we prefer the use of Array.prototype.forEach over angular.forEach over $.each
* We use the IIFE design pattern
  * This allows for a defined execution context for each code block
  * This ensures privacy of code blocks
* We declare globals as parameters for our IIFE
  * This clearly identifies the globals that will be used
  * This ensures that globals in the context of the IIFE are as expected
```javascript
  (function(angular) {
    /* code block */
  }(angular));
```
* We use strict mode for all javascript execution
  * We value failing fast and encourage all errors to be thrown
  * We value security and the enhancements enforced by strict more
* We prefer descriptive names for functions and variables rather than commenting
  * Of course, sometimes that may not be enough, and a comment would aide in understanding, left to developer's discretion

## AngularJS Naming Standards
* Modules should be defined in a parent folder with granular division of Angular component files in individual folders divided by business value
  * This follows concepts used by Java packages
  * This seperates file groups by business value and concern
```
  /Stores
  ../Configuration
  ../../application.store.service.js
  ../../organization.store.service.js
  /Configuration
  ../Owner
  ../../owner.editor.directive.js
  ../../owner.editor.directive.html
  ../Label
  ../../label.controller.js
  ../../label.view.html
```
* Files should be named after their contents and suffixed by the type of Angular component
  * This allows developers to easily find code
  * foo.controller.js
```javascript
  angular.controller('foo.controller', FooController);
```
* Angular component names, except for directives, should be suffixed by their type
  * Appending the component type to its name allows the consumer to easily understand its type and function
  * Directives are not suffixed to prevent the extra HTML markup required to reference them
  * qux.directive.js
```javascript
  angular.directive('qux', QuxDirective);
```
* Angular templates should share the name of their directive or view
  * qux.directive.html <-> qux.directive.js
  * baz.view.html <-> baz.controller.js

## AngularJS Development
* Each Angular Component should exist in it's own file
  * We value the ability to easily find where code resides
  * We understand that small code files encourages the decomposition of a problem
  * We follow other language practices of encapsolating one object into one file
* Each Angular template and view should exist in it's own file
  * We value the ability to easily access code
  * This allows the view controller and directive to reside in the file system beside its business logic
* Javascript code should not contain HTML
  * This enforces the separation of view and controller
  * Performance concerns can be mitigated by compiling the markup into Javascript during the build
  * HTML in Javascript is difficult to read and maintain
* Component function should exist at the top of the code file
  * This highlights the core logic over the boiler plate code
  * This prevents unnecessary abuse of Javascript's hoisting
```javascript
  function FooController() {
    /* code block */
  }
  angular.controller('foo.controller', FooController);
```
* We utilize an Angular components $inject array to declare dependencies
  * This prevents long lists of dependencies to interfect with code legibility
  * This prevents code churn from how Eclipse and IDEA format these arrays
```javascript
  function FooController($state) {
    /* code block */
  }
  FooController.$inject = ['$state'];
```
#### HTML
* Native attributes should be defined prior to custom attributes
  * This increases readabilities due to the ordering/organizing of the attributes
```html
  <textarea id="foo" class="bar" name="name" rows="2" ng-model="baz" ng-maxlength="255"></textarea> 
```

# Jasmine Development
* Jasmine root describe should share the name of the containing file
  * This allows developers to easily locate code for failing tests
* There should be a single jasmine file for each Angular component
  * This mirrors the file structure of the source code
  * This enforces smaller test files for easy consumption
