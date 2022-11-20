package com.xonicsports_sportnews.dataClasses

data class FlexTonics(
    val freePredictions: List<FreePrediction>,
    val paidPredictions: List<PaidPrediction>,
    val plans: List<Plan>,
    val predictionResults: List<PredictionResult>,
    val users: List<User>
)