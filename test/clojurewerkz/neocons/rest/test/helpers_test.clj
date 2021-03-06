;; Copyright (c) 2011-2014 Michael S. Klishin, Alex Petrov, and The ClojureWerkz
;; Team
;;
;; The use and distribution terms for this software are covered by the
;; Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this distribution.
;; By using this software in any fashion, you are agreeing to be bound by
;; the terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns clojurewerkz.neocons.rest.test.helpers-test
  (:require [clojurewerkz.neocons.rest.records :as rec]
            [clojure.test :refer :all]
            [clojurewerkz.neocons.rest.helpers :refer :all]))

(deftest test-id-extraction
  (is (= 1 (extract-id "http://localhost:7474/db/data/node/1")))
  (is (= 10 (extract-id"http://localhost:7474/db/data/node/10")))
  (is (= 100 (extract-id"http://localhost:7474/db/data/node/100")))
  (is (= 1000 (extract-id"http://localhost:7474/db/data/node/1000")))
  (doseq [id (range 1 10000)]
    (is (= id (extract-id (str "http://localhost:7474/db/data/node/" id))))))


(deftest test-instantiate-record-from
  (is (instance? clojurewerkz.neocons.rest.records.Node
                 (rec/instantiate-record-from {:traverse "http://localhost:7474/db/data/node/1783/traverse/{returnType}",
                                               :data {},
                                               :extensions {},
                                               :outgoing_relationships "http://localhost:7474/db/data/node/1783/relationships/out",
                                               :incoming_typed_relationships "http://localhost:7474/db/data/node/1783/relationships/in/{-list|&|types}",
                                               :incoming_relationships "http://localhost:7474/db/data/node/1783/relationships/in",
                                               :all_typed_relationships "http://localhost:7474/db/data/node/1783/relationships/all/{-list|&|types}",
                                               :paged_traverse "http://localhost:7474/db/data/node/1783/paged/traverse/{returnType}{?pageSize,leaseTime}",
                                               :properties "http://localhost:7474/db/data/node/1783/properties",
                                               :property "http://localhost:7474/db/data/node/1783/properties/{key}",
                                               :all_relationships "http://localhost:7474/db/data/node/1783/relationships/all",
                                               :outgoing_typed_relationships "http://localhost:7474/db/data/node/1783/relationships/out/{-list|&|types}",
                                               :self "http://localhost:7474/db/data/node/1783",
                                               :create_relationship "http://localhost:7474/db/data/node/1783/relationships"})))
  (is (instance? clojurewerkz.neocons.rest.records.Relationship
                 (rec/instantiate-record-from {:extensions {},
                                               :start "http://localhost:7474/db/data/node/1987",
                                               :property "http://localhost:7474/db/data/relationship/92/properties/{key}",
                                               :self "http://localhost:7474/db/data/relationship/92",
                                               :properties "http://localhost:7474/db/data/relationship/92/properties",
                                               :type "knows",
                                               :end "http://localhost:7474/db/data/node/1988",
                                               :data {}}))))
