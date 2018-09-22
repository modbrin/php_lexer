# PHP Lexer
Compilers Construction Course Project
## Authors
- Maxim Surkov (m.surkov@innopolis.ru)
- Ivan Lyagaev (i.lyagaev@innopolis.ru)

## How to run
Run the script `sh script_for_run` \
__OR__ \
Command: `./gradlew run`

## Documentation

We have 6 groups of Tokens:
- Identifier - variables, function/class identifiers (e.g. `$var`,`get_all_marks`)
- Operator - arithmetic, logic, bitwise, ... (e.g. `+`, `&`, `>>`)
- Keyword - [all](http://php.net/manual/en/reserved.keywords.php) php keywords (e.g. `function`, `class`)
- Comment - three types of comments (e.g. `/* COMMENT */`, `// COMMENT`,  `# COMMENT`)
- Literal - `"LITERAL"`, `'LITERAL'`, `true`, `false`, `null`, `10`, `10.01`, `1e4`
- Delimiter - `\n`, `\r`, `\r\n`, ` `, `;`, `(`, `)`, `{`, `}`, `[`, `]`

#### Output format

`<TOKEN_NAME 'STRING'>`
- _TOKEN_NAME_ - name of the token
- _STRING_ - string that was identified as a token 
