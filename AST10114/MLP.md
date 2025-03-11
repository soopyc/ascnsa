> Q1: Understanding the Output Error Calculation:

The derivative is already included in the backpropagation algorithm.

> Q2: Influence of the Number of Hidden Neurons:

Higher K increases training speed and generally the error rate as well.

> Q3: Influence of the Learning Rate:

High training rate results in faster training but unstable error rate and will not be as accurate.

Lower training rates give higher accuracy.

> Q4: Influence of Weight Initialization:

The network does not learn after 1k epochs. If all of the initialized weights are the same, backpropagation doesn't work properly since everything changes by the same amount.

> Q5: Analyzing Multiple Training Runs with Random Initializations:

The graphs show different results based on the initialization, which provides variation.

> Q6: Influence of Data Scaling (Shifting):

It's terrible, doesn't train at all.

> Q7: Data Normalization:

Suddenly becomes the best classifier on the planet, trains under 1 second. I might have messed up the code though.

> Q8: Cross-Validation and Optimization:

Changing the K value is the most effective in reducing epochs to converge.

---

Notebook with modifications: https://colab.research.google.com/gist/soopyc/be1a4b4e26caf75bfbf1044b10c10f23/mlp.ipynb
