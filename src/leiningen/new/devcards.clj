(ns leiningen.new.devcards
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "devcards"))

(defn devcards
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' devcards project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["devcards_src/{{sanitized}}_devcards/core.cljs" (render "devcards-core.cljs" data)]
             ["resources/public/devcards/index.html" (render "resources/index.html" data)]
             ["resources/public/devcards/css/devcards.css" (render "resources/css/devcards.css" data)]
             ["resources/public/devcards/css/rendered_edn.css" (render "resources/css/rendered_edn.css" data)]
             ["resources/public/css/{{sanitized}}_style.css" (render "style.css" data)]             
             ["src/{{sanitized}}/core.cljs" (render "core.cljs" data)])))