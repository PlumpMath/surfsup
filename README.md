# surfsup

## Goal

Lift the browser into the Clojure application development cycle - even
for applications that are not web apps.


## Requirements

surfsup uses a Clojurescript browser repl environment.
surfsup/make-browser-repl-env will start the Clojure side of repl
environment (on port 9000 by default). 

If you're using surfsup as a library, you should host a page that
correctly connects to the Clojure side by calling
clojure.browser.repl/connect (see src/cljs/surfsup/browser.cljs and
resources/public/repl.html for an example).

## Part 1
A manual 'repl'.
* start a browser env
* let the browser connect
* start visualizing your application

```clojure
;; assuming you have a server serving a 
;; page that calls clojure.browser.repl/connect
(require '[surfsup.core :as s])
(def browser-env (s/make-browser-repl-env))
(s/surfsup browser-env {:a 1 :b 2})
(s/surfsup browser-env browser-env)
```

You can also checkout the surfsup repository to explore examples
```sh
git clone https://github.com/musicalchair/surfsup.git
cd surfsup
lein repl
```

```clojure
(require 'dev)
(dev/dev-start)

(require '[surfsup :as s])
(s/surfsup (dev/dev-context :repl) dev/dev-context)
```
