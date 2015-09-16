(ns msg-ops.system
  (:require [com.stuartsierra.component :as component]
            [modular.bidi]
            [modular.http-kit]
            [modular.ring]
            [ring.middleware.params :as r.m.params]
            [ring.middleware.format :as r.m.format]
            [ring.middleware.defaults :as r.m.defaults]
            [msg-ops.components.rainforestqa]
            [me.raynes.fs :as fs]
            [com.stuartsierra.component :as component]
            [environ.core :refer [env]]
            [ring.middleware.basic-authentication :refer [wrap-basic-authentication]]))

(defn prod-system
  [{:keys [salesforce uri] :as config}]
  (component/system-map

    :http-server
    (component/using
      (modular.http-kit/new-webserver
        :port (Integer. (or (env :port) 3000)))
      [:router-head])

    :router-head
    (component/using
      (modular.ring/new-web-request-handler-head)
      {:request-handler :bidi-router
       :middleware      :middleware})

    :middleware
    (fn [h]
      (-> h
          r.m.params/wrap-params
          r.m.format/wrap-restful-format
          #_(wrap-basic-authentication (fn [user pass]
                                       (and (= user "jtackett" ;(env :basic-username)
                                               )
                                            (= pass "jt" ;(env :basic-password)
                                               )

                                            )))
          ;(r.m.defaults/wrap-defaults
          ;  (assoc r.m.defaults/site-defaults :keywordize true))
          ))

    :bidi-router
    (component/using
      (modular.bidi/new-router)
      [:rainforest-hook])

    :rainforest-hook
    (component/using
      (msg-ops.components.rainforestqa/new-hook)
      [])))

