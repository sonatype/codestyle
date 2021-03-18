# ESLint style rules

For projects that use ESLint to check for and fix style issues in their code, we
export [shareable eslint configs](https://eslint.org/docs/developer-guide/shareable-configs) with our code-styles
builtin.

These configs can be installed and used in conjunction with other configs and extended configs by defining an extension
in your `.eslintrc.*` like so:

```
{
    "extends": "@sonatype/eslint-config-js"
}
```

or, in a shorthand that ESLint supports,

```
{
    "extends": "@sonatype/js"
}
```
