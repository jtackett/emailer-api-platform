(ns user
  #_(:require [msg-ops.system :refer [prod-system]]
            [environ.core :refer [env]]
            [reloaded.repl :refer [system init start stop go reset]]))

#_(defn run []
  (let [salesforce {:client-id      (env :client-id)
                    :client-secret  (env :client-secret)
                    :username       (env :username)
                    :password       (env :password)
                    :security-token (env :security-token)}
        msg-auth (env :msg-auth)]

    (reloaded.repl/set-init!
      #(prod-system {:salesforce salesforce
                    :msg-auth   msg-auth}))

    (reloaded.repl/reset)))