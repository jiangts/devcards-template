(ns leiningen.new.devcards
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "devcards"))

(defn devcards
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info (str "Generating fresh 'lein new' devcards project.\n"
                    "cd into your project directory\n"
                    "Run 'lein figwheel' and then open localhost:3449/devcards.html in your browser."
                    ""))
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["resources/public/devcards.html" (render "resources/index.html" data)]
             ["resources/public/index.html" (render "resources/app_index.html" data)]             
             ["resources/public/css/{{sanitized}}_style.css" (render "style.css" data)]             
             ["src/{{sanitized}}/core.cljs" (render "core.cljs" data)])))
