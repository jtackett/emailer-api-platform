(ns msg-ops.components.rainforestqa
  (:require [bidi.bidi :refer [RouteProvider]]
            [postal.core :as mail]))

(defn create-csv [fields]
  (let [headers (map name (keys fields))
        values  (vals fields)]
    (str
     (clojure.string/join "," headers)
     "\n"
     (clojure.string/join "," values))))

(create-csv {:data "3mb"
             :speakers 5
             :notes "The carpet is thick"})

(defn write [path content]
  (spit path (if content
               content
               "Empty file"))
  (java.io.File. path))


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
                        message (:message bd)
                        file (create-csv fields)]
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
                             {:type :attachment
                              :content (write "/tmp/test.csv" file)}]}))
                  )}]))

(defn new-hook []
  (map->RainforestQA {}))
