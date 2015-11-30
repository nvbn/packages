(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/bootlaces   "0.1.9" :scope "test"]
                  [cljsjs/boot-cljsjs "0.5.0"  :scope "test"]
                  [cljsjs/react "0.14.3-0"]])

(require '[adzerk.bootlaces :refer :all]
         '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "0.5.6")
(def +version+ (str +lib-version+ "-0"))
(bootlaces! +version+)

(task-options!
 pom  {:project     'cljsjs/react-input-mask
       :version     +version+
       :description "Auto-resizing input field for React"
       :url         "http://jedwatson.github.io/react-input-mask/"
       :scm         {:url "https://github.com/cljsjs/packages"}
       :license     {"MIT" "http://opensource.org/licenses/MIT"}})

(require '[boot.core :as c]
         '[boot.tmpdir :as tmpd]
         '[clojure.java.io :as io]
         '[clojure.string :as string])

(deftask package []
  (comp
    (download :url "http://sanniassin.github.io/react-input-mask/javascripts/InputElement.js"
	      :checksum "CF24D3069E1A47DDAA5A708BF4E61D9E")

    (sift :move {#"^InputElement.js$" "cljsjs/react-input-mask/development/react-input-mask.inc.js"})

    (sift :include #{#"^cljsjs"})
    (deps-cljs :name "cljsjs.react-input-mask"
               :requires ["cljsjs.react"])))
