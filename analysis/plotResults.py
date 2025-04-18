import os
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

# define file path and load data from csv
directory = os.path.dirname(os.path.abspath(__file__))
file_path = os.path.join(directory, "sort_results.csv")
df = pd.read_csv(file_path)

# make sure k and n treated as numbers
df['K'] = pd.to_numeric(df['K'])
df['N'] = pd.to_numeric(df['N'])

# Specify configurations for bar graphs
plot_configs = [
    {
        "N": 1000,
        "K_range": (1000, 10_000_000),
        "algorithms": ["CountingSort", "ARUCountingSort"],
        "y_ticks": np.arange(0.0, 1.61, 0.2),
        "y_lim": (0.0, 1.6),
        "title": "Time Complexity (N = 1,000)",
        "filename": "plot_N_1000.png"
    },
    {
        "N": 10000,
        "K_range": (0, 10_000_000),
        "algorithms": None,
        "title": "Time Complexity (N = 10,000)",
        "filename": "plot_N_10000.png"
    },
    {
        "N": 100000,
        "K_range": (100000, 1_000_000_000),
        "algorithms": ["ARUCountingSort", "QuickSort", "MergeSort", "RadixSort"],
        "title": "Time Complexity (N = 100,000)",
        "filename": "plot_N_100000.png"
    },
    {
        "N": 1_000_000,
        "K_range": (100000, 1_000_000_000),
        "algorithms": ["ARUCountingSort", "QuickSort", "MergeSort", "RadixSort"],
        "title": "Time Complexity (N = 1,000,000)",
        "filename": "plot_N_1000000.png"
    }
]

# colours for each algorithm
colours = {
    "CountingSort": "#FF6666",
    "ARUCountingSort": "#4C9900",
    "QuickSort": "#FF99FF",
    "MergeSort": "#FF9933",
    "RadixSort": "#808080"
}

# loop for bar plots
for config in plot_configs:
    n_val = config["N"]
    k_min, k_max = config["K_range"]
    allowed_algs = config["algorithms"]

    # filter data for each bar chart
    subset = df[(df["N"] == n_val) & (df["K"] >= k_min) & (df["K"] <= k_max)]
    if allowed_algs:
        subset = subset[subset["Algorithm"].isin(allowed_algs)]

    # bar chart
    plt.figure(figsize=(14, 8))
    sns.barplot(data=subset, x="K", y="AvgTime(s)", hue="Algorithm", palette=colours, edgecolor="black", width=0.5, linewidth=0.5)

    plt.title(config["title"])
    plt.xlabel("Maximum Value of Elements (k)")
    plt.ylabel("Average Time (s)")
    plt.xticks(rotation=45)

    plt.legend(title="Algorithms")
    plt.tight_layout()
    plt.savefig(config["filename"])
    plt.show()
    
# line graph

algorithms = ["ARUCountingSort", "QuickSort", "MergeSort", "RadixSort"]
sub = df[df["Algorithm"].isin(algorithms) & (df["N"] ==  10000)].copy()

# convert time to milliseconds for line graph
sub["AvgTime(ms)"] = sub["AvgTime(s)"] * 1000

# plot
plt.figure(figsize=(14, 8))

for alg in algorithms:
    alg_data = sub[sub["Algorithm"] == alg].sort_values(by="K")
    if not alg_data.empty:
        sns.lineplot(data=alg_data, x="K", y="AvgTime(ms)", label=alg, marker="o", linewidth=2, color=colours.get(alg, None))

plt.title("Time Complexity (N = 10,000)")
plt.xlabel("Maximum Value of Elements (K)")
plt.ylabel("Average Time (ms)")

plt.xscale("log")
plt.xlim(left=10000)
plt.xticks([10_000, 100_000, 1_000_000, 10_000_000, 100_000_000, 1_000_000_000], labels=["10K", "100K", "1M", "10M", "100M", "1B"])

plt.legend(title="Algorithms")
plt.tight_layout()
plt.grid(True, linestyle='--', linewidth=0.5, alpha=0.7)
plt.savefig("line_plot.png")
plt.show()

