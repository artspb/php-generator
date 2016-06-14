<?php
function foo(string $bar, $baz = 0): int {
}
interface QuxInterface {
    function foo(string $bar, $baz = 0): string;
    function bar();
    const CONSTANT = "VALUE";
}
abstract class FooClass implements QuxInterface {
}
class BarClass extends FooClass {
    /**
     * @param string $bar
     * @param int $baz
     * @return string
     * @throws Exception
     */
    public function foo(string $bar, $baz = 0): string {
    }
    function bar() {
    }
    var $qux;
    const TMP_CNST = 100500;
}
