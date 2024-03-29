<?php
/**
 * Accept any expression
 *
 * @param   string  $query
 * @return  ExpressionInterface
 */
protected static function acceptExpression(array &$tokens)
{
    if (empty($tokens)) {
        return null;
    }
    $token = array_shift($tokens);
    $expression = null;
    switch ($token->type) {
        case Token::T_LEXEME:
            $expression = new Lexeme($token->data);
            if (!empty($tokens)) {
                $nextToken = array_shift($tokens);
                if (Token::T_LEXEME_PREFIX === $nextToken->type) {
                    $expression->setIsPrefix();
                } else {
                    array_unshift($tokens, $nextToken);
                }
            }
            break;
        case Token::T_PHRASE:
            $expression = new Phrase(trim(preg_replace('/\\s+/', ' ', $token->data)));
            break;
        case Token::T_OPERATOR_NOT:
            $innerExpression = static::acceptExpression($tokens);
            if ($innerExpression) {
                $expression = new NotExpression($innerExpression);
            }
            break;
        case Token::T_SET_OPEN:
            if (empty($tokens)) {
                break;
            }
            $expression = static::acceptExpressionSet($tokens);
            $expressions = $expression->getExpressions();
            if (count($expressions) === 1) {
                $expression = reset($expressions);
            }
            break;
        case Token::T_OPERATOR_OR:
        case Token::T_OPERATOR_AND:
        case Token::T_LEXEME_PREFIX:
        case Token::T_SET_CLOSE:
        default:
            // skip
            break;
    }
    return $expression;
}
