
(defproject writer "1.0.0-SNAPSHOT"
  :description "a knowledge,note writer"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.1"]
                 [midje "1.4.0"]
                 [kerodon "0.0.7"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [hiccup "1.0.0"]
                 [org.clojure/tools.nrepl "0.2.0-beta10"]
                 [mysql/mysql-connector-java "5.1.21"]]
  :plugins [[lein-ring "0.7.1"]
            [lein-cucumber "1.0.0"]]
  :ring {:handler writer.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.2"]]
         :plugins [[lein-midje "2.0.1"]]}
   })
