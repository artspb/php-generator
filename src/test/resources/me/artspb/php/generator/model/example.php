<?php
// normal comment
// normal
// multiline
// comment
# sharp comment
# sharp
# multiline
# comment
/* delimited comment */
/* delimited
 multiline
 comment */
namespace NS1\NS2 {
    class NamespaceClass {
    }
    function namespaceFoo() {
    }
}
namespace NS {
    use NS1\NS2\NamespaceClass;
    use function NS1\{
        NS2\namespaceFoo
    };
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
}
