# eslint-config-ts

ESLint TypeScript config for Sonatype's code-style.

This config is an extension of our base [eslint-config-js](../eslint-config-js) mixed with
[Google's TypeScript style guide](https://github.com/google/gts). It should also export all the dependencies necessary
to perform TypeScript linting, so consumers should not need to also install parsers or plugins for ESLint.
