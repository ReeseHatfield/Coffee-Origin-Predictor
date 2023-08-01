import pandas as pd
import numpy as np

# Load the data
df = pd.read_csv('final_clean_data.csv')

# Choose which columns to tweak
cols_to_tweak = ["Aroma","Flavor","Aftertaste","Acidity","Body","Balance",
                 "Uniformity","Clean.Cup","Sweetness","Cupper.Points","Total.Cup.Points","Moisture"]

# Number of duplicates for each instance
num_duplicates = 5

# Tweak range
tweak_range = 0.05

# Create empty dataframe to hold augmented data
df_augmented = pd.DataFrame()

for _ in range(num_duplicates):
    df_new = df.copy()
    for col in cols_to_tweak:
        tweaks = np.random.uniform(-tweak_range, tweak_range, df_new.shape[0])
        df_new[col] = df_new[col] + tweaks
    df_augmented = df_augmented._append(df_new, ignore_index=True)

# Combine original and augmented data
df_total = df._append(df_augmented, ignore_index=True)

# Save the augmented data to a new CSV file
df_total.to_csv('augmented_data.csv', index=False)
