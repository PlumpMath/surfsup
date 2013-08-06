(defproject surfsup "0.1.0-SNAPSHOT"
  :description "For surfing driven development"
  :url "http://github.com/musicalchair/surfsup"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-1835"]
                 [ring "1.2.0"]
                 [com.cemerick/piggieback "0.0.5"]
                 [hiccup "1.0.3"]]

  :aliases {"brepl" ["trampoline" "cljsbuild" "repl-listen"]}


  
  :plugins [[lein-cljsbuild "0.3.2"]]
  :source-paths ["src/clj"]  
  :hooks [leiningen.cljsbuild]

  :cljsbuild {:builds
              [{:source-paths ["src/cljs"]
                :compiler {:output-to "resources/public/js/surfsup.js"
                           :optimizations :whitespace}}]}

  :profiles {:dev
             {:source-paths ["dev"]
              :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
