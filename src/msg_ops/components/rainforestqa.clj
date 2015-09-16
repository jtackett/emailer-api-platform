(ns msg-ops.components.rainforestqa
  (:require [bidi.bidi :refer [RouteProvider]]
            [postal.core :as mail]))

(defn create-csv []
  )

;; Sample input
;;{:to "joshtackett7@gmail.com"
;; :cc "jtackett@villanova.edu"
;; :subject "New Network"
;; :message "Please draw up a design for this"
;; :fields {:data "3mb"
;;          :speakers 5
;;          :notes "The carpet is thick"}}

(defrecord RainforestQA []
  RouteProvider
  (routes [this]
          ["/" {"email"
                (fn [{:keys [params body] :as req}]
                  (let [bd (read-string (slurp body))
                        to (:to bd)
                        cc (:cc bd)
                        subject (:subject bd)
                        fields (:fields bd)
                        message (:message bd)]
                    (prn (str "THIS IS THE REQUEST + = = = =" req))
                    (prn " + + + + + + + + ")
                    (prn (slurp body))
                    (mail/send-message
                     ;; Constant (Always from MIS app email)
                     {:host "smtp.gmail.com"
                      :user "t.j.hudson.publications"
                      :pass "cet0202="
                      :ssl :yes!!!11}
                     {:from "t.j.hudson.publications@gmail.com"
                      :cc cc
                      :to to
                      :subject subject
                      :body [{:type "text/html"
                              :content message}
                             #_{:type :attachment
                                :content (java.io.File. "/tmp/foo.txt")}]}))
                  )}]))

(defn new-hook []
  (map->RainforestQA {}))
