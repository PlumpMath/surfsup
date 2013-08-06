(ns surfsup.core
  (:require [cljs.repl :as repl]
            [hiccup.core :as hiccup]))

(defprotocol HiccupRenderable
  (render-hiccup [this]))

(extend-type clojure.lang.IPersistentMap
  HiccupRenderable
  (render-hiccup [m]
    [:dl
     (mapcat (fn [[k v]]
               [[:dt (render-hiccup k)]
                [:dd (render-hiccup v)]])
             m)]))

(extend-type clojure.lang.IPersistentVector
  HiccupRenderable
  (render-hiccup [v]
    [:ol
     (map (fn [e] [:li e]) v)]))


(extend-type Object
  HiccupRenderable
  (render-hiccup [o] (str o)))

(defn render [data]
  (let [html (-> data render-hiccup hiccup/html)]
    `(let [body# (-> js/document (.getElementsByTagName "body") (aget 0))
           el#   (.createElement js/document "div")]
       (set! (. el# ~'-innerHTML) ~html)
       (.appendChild body# el#))))

(def blank-eval-env {:context :expr
                     :locals {}
                     :ns nil})

(defn surfsup [repl-env data]
  (repl/evaluate-form repl-env blank-eval-env "<surfsup>" (render data)))
