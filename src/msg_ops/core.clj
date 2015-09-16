(ns msg-ops.core
  (:require [msg-ops.system :refer [prod-system]]
            [com.stuartsierra.component :as component]
            [environ.core :refer [env]]
            [taoensso.timbre :as log])
  (:gen-class))


(defn start-system []

  (println "Starting msg-ops")

  (let [salesforce {:client-id      (env :client-id)
                    :client-secret  (env :client-secret)
                    :username       (env :username)
                    :password       (env :password)
                    :security-token (env :security-token)}
        msg-auth (env :msg-auth)
        uri (env :uri)]

    (component/start-system
      (prod-system {:salesforce salesforce
                    :msg-auth   msg-auth
                    :uri        uri})))

  (println "System started")
  (println "Ready..."))

(defn -main
  []
  (start-system))

