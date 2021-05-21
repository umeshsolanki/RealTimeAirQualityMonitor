package com.airqualitymonitor.umesh.ui.base

/**
 * BaseViewHolderModel contains layoutRes to take a lot of decisions while populating data in Recyclerview's
 * View Holder and other business logic, Currently using 2 ViewHolderModel: ChartViewHolderModel and  CityAQIViewHolderModel
 */
interface BaseViewHolderModel {
    var layoutRes: Int
}