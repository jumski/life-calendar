(ns ^:dev/always jumski.life-calendar.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [jumski.life-calendar.calendar :as calendar]
            [cljs-time.core :as t]))

; defonce so we do not lose state when hot reloading
(def my-birth-date (t/date-time 1985 12 25))
(defonce *birth-date (r/atom my-birth-date))

(defn week->css-class [week birth-date]
  (cond
    (calendar/same-week? week (t/now)) :current
    (calendar/week-before? week (t/now)) :past
    :else :future))

(defn life-calendar [birth-date]
  [:table#calendar
   [:tbody
    (for [year (calendar/create-for-date birth-date)]
      ^{:key year} [:tr (for [week year
                 :let [wtype (week->css-class week birth-date)]]
             ^{:key week} [:td {:class wtype :omg "hax"}])])]])

(defn ui []
  (doall
    (let [birth-date @*birth-date]
      [:div
       [life-calendar birth-date]])))

(defn render-ui []
  (let [dom-node (js/document.getElementById "app")]
    (rdom/render [ui {:dummy (js/Date.now)}] dom-node)))

(defn ^:export reload! []
  (render-ui))

(def ^:export main! render-ui)
