(defproject
  msg-ops "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.5.0"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :main msg-ops.core
  :dependencies [[org.clojure/clojure "1.6.0"]

                 [cheshire "5.4.0"]
                 [bidi "1.19.0"]
                 [juxt.modular/bidi "0.9.2"]
                 [juxt.modular/http-kit "0.5.4"]
                 [juxt.modular/ring "0.5.2"]
                 [ring/ring-defaults "0.1.4"]
                 [ring-middleware-format "0.4.0"]
                 [me.raynes/fs "1.4.6"]
                 [org.danielsz/system "0.1.7"]

                 ;; HTTP
                 [clj-http "1.1.2"]

                 ;; Logging
                 [com.taoensso/timbre "3.3.1"]

                 ;; Emailer library
                 [com.draines/postal "1.11.1"]

                 ;; Env Vars
                 [environ "1.0.0"]

                 ;; Auth
                 [ring-basic-authentication "1.0.5"]]

  :repl-options {:init-ns user}
  :profiles {:uberjar {:aot :all}}
  :uberjar-name "msg-ops.jar")
