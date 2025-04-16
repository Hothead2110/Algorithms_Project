import os
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np
from matplotlib.ticker import FuncFormatter

# define file path and load data from csv
directory = os.path.dirname(os.path.abspath(__file__))
file_path = os.path.join(directory, "sort_results.csv")
df = pd.read_csv(file_path)

# make sure k and n treated as numbers
df['K'] = pd.to_numeric(df['K'])
df['N'] = pd.to_numeric(df['N'])

# Specify configurations for four graphs
plot_configs = [
    {
        "N": 1000, # filter rows where N=1000
        "K_range": (1000, 10_000_000), # k value from 1000 to 10,000,000
        "algorithms": ["CountingSort", "ARUCountingSort"], # only graph these algorithms
        "y_ticks": np.arange(0.0, 1.61, 0.2), 
        "y_lim": (0.0, 1.6),
        "title": "Time Complexity (N = 1,000)",
        "filename": "plot_N_1000.png"
    },
    {
        "N": 10000,
        "K_range": (10000, 10_000_000),
        "algorithms": None,  # All
        #"y_ticks": np.arange(0.00, 0.161, 0.02),
        #"y_lim": (0.0, 0.16),
        "title": "Time Complexity (N = 10,000)",
        "filename": "plot_N_10000.png" # output file
    },
    {
        "N": 100000,
        "K_range": (100000, 1_000_000_000),
        "algorithms": ["ARUCountingSort", "QuickSort", "MergeSort"],
        #"y_ticks": np.arange(0.00, 0.101, 0.01),
        #"y_lim": (0.0, 0.10),
        "title": "Time Complexity (N = 100,000)",
        "filename": "plot_N_100000.png"
    },
    {
        "N": 1_000_000,
        "K_range": (100000, 1_000_000_000),
        "algorithms": ["ARUCountingSort", "QuickSort", "MergeSort"],
        #"y_ticks": np.arange(0.0, 1.01, 0.1),
        #"y_lim": (0.0, 1.0),
        "title": "Time Complexity (N = 1,000,000)",
        "filename": "plot_N_1000000.png"
    }
]

# colours for each algorithm
colours = {
    "CountingSort": "#FF6666",
    "ARUCountingSort": "#4C9900",
    "QuickSort": "#FF99FF",
    "MergeSort": "#FF9933"
}

# loop for every graph and save plots
for config in plot_configs:
    n_val = config["N"]
    k_min, k_max = config["K_range"]
    allowed_algs = config["algorithms"]

    # filter data according to current graph
    subset = df[(df["N"] == n_val) & (df["K"] >= k_min) & (df["K"] <= k_max)]
    # filter by specific algorithms if defined
    if allowed_algs:
        subset = subset[subset["Algorithm"].isin(allowed_algs)]

    # create figure for chart
    plt.figure(figsize=(14, 8))
    sns.barplot(data=subset, x="K", y="AvgTime(s)", hue="Algorithm", palette=colours, edgecolor="black", width=0.5, linewidth=0.5) # bar plot with seaborn

    # add title and labels
    plt.title(config["title"])
    plt.xlabel("Maximum Value of Elements (k)")
    plt.ylabel("Average Time (s)")
    plt.xticks(rotation=45)

    # add legend
    plt.legend(title="Algorithms")
    plt.tight_layout()

    # save plot
    plt.savefig(config["filename"])
    #plt.show()

