(ns ^:dev/always jumski.life-calendar.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [jumski.life-calendar.calendar :as cal]
            [cljs-time.core :as t]))

; defonce so we do not lose state when hot reloading
(defonce *birth-date (r/atom (t/date-time 1985 12 25)))


(defn life-calendar [birth-date]
  [:table
   [:tbody
    (for [year (cal/life-calendar birth-date)]
      [:tr (for [week year
                 :let [wtype (cal/week->type week birth-date)]]
             [:td (str \[ wtype \])])])]])

(defn ui []
  (doall
    (let [birth-date @*birth-date]
      [:div
       [life-calendar birth-date]])))

(defn render-ui []
  (let [dom-node (js/document.getElementById "app")]
    (rdom/render [ui] dom-node)))

(defn ^:export reload! []
  (render-ui))

(def ^:export main! render-ui)
