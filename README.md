# Coffee Origin Predictor
A machine learning algorithm to determine the origin of speciality coffee. 
The model can take in 9 parameters and predict a single country of origin for the brew, with up to __**85%**__ accuracy


## How does this work?
This machine learning model predict origins by using [Weka](https://www.cs.waikato.ac.nz/ml/weka/)'s implementation
of the [C4.5](https://en.wikipedia.org/wiki/C4.5_algorithm) decision tree algorithm ([J48](https://weka.sourceforge.io/doc.dev/weka/classifiers/trees/J48.html)). It is considered relatively balanced classification problem.

## Parameters:
- **Aroma**: The smell of the coffee, both when it's dry, and after it's been brewed.
- **Flavor**: The overall taste of the coffee when you drink it, including its depth and complexity.
- **Aftertaste**: The lingering taste in your mouth after you've drank the coffee.
- **Acidity**: The sharp, bright quality of the coffee, often associated with fruitiness, wine-like flavors, or a sensation of liveliness on the palate.
- **Body**: The weight or thickness of the coffee in your mouth, which can range from light and tea-like to heavy and syrupy.
- **Balance**: How the different flavors in the coffee interact and complement each other, and whether any flavor dominates the others.
- **Uniformity**: How consistent the coffee's taste is from cup to cup. High uniformity means every cup tastes the same.
- **Cup Cleanliness**: The absence of undesirable or distracting flavors in the coffee. A clean cup doesn't have any off or strange flavors.
- **Sweetness**: The degree to which the coffee has a sugary or sweet taste, often balancing out the bitterness or acidity.
## Training data
The source data was obtained from the [Coffee Quality Institute](https://database.coffeeinstitute.org/). 
This data was further augmented (approximately 5x) using a custom [synthetic augmentation algorithm](https://github.com/ReeseHatfield/Coffee-Origin-Prediction-AI/blob/0ed0d440a14fc65707016f18da6b1c068ba71a3a/python_utils/data_augmentation.py)

## Limitations

### Arabica Only
The model can only predict coffee from the arabica species, as the Robusta data 
proved to be too limiting for accurate prediction

### Specialty Coffee Only
The model can only be used to predict the origins of specialty coffees. 
This is because non-speciality coffees 
lack the proper identifying profile to pinpoint specific origins

### Taster Pallets and Accuracy

Each coffee tasters is prone to be more sensitive to certain categories. This results in slightly different profiles, 
leading to different possible origin predictions


