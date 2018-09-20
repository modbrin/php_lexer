

function console_log( $data ){
  echo '<script>';
  echo 'console.log('. json_encode( $data ) .')';
  echo '</script>';
}

$source = <<<'code'
<?php

class A
{
    const PUBLIC = 1;
    $stack = console_log("kek")
}
?>
code;

$tokens = token_get_all($source);

$out = array();

foreach ($tokens as $token) {
    if (is_array($token)) {
        $token_name = token_name($token[0]);
        array_push($out, "Line {$token[2]}: {$token_name}('{$token[1]}')");
    }
}


console_log($out);
