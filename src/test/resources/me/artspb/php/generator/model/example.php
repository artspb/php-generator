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
    define("NS1\\NS2\\CONSTANT1", "1");
    define("NS1\\NS2\\CONSTANT2", "2");
}
namespace NS {
    use NS1\NS2\NamespaceClass as Clazz;
    use function NS1\{
        NS2\namespaceFoo
    };
    use const NS1\{
        NS2\CONSTANT1,
        NS2\CONSTANT2 as CNST
    };
    function foo(string $bar, $baz = 0): int {
    }
    interface QuxInterface {
        function foo(string $bar, $baz = 0): string;
        function bar();
        const CONSTANT = "VALUE";
    }
    trait FooTrait {
        function foo() {
        }
    }
    trait BarTrait {
        function foo() {
        }
    }
    abstract class FooClass implements QuxInterface {
        use FooTrait {
            foo as private;
            foo as bar;
            foo as private bar;
        }
        use FooTrait, BarTrait {
            FooTrait::foo insteadof BarTrait;
        }
    }
    class BarClass extends FooClass {
        /**
         * @param string $bar
         * @param int $baz
         * @return string
         * @throws \Exception
         */
        public function foo(string $bar, $baz = 0): string {
        }
        function bar() {
        }
        var $qux;
        const TMP_CNST = 100500;
    }
}
