# Project Notice

This folder is a cleaned public-portfolio version of an individual Bachelor coursework submission completed in 2022.

## Preserved Material

- the original analytical workflow and saved output cells
- the three source question-and-answer datasets
- exploratory visualizations
- topic-modeling and clustering experiments
- logistic-regression, neural-network, and XGBoost classification experiments

## Public Cleanup

The public version:

- removes the student identification number from filenames and metadata
- replaces the original notebook filename with a descriptive project name
- converts source data to UTF-8 TSV files with consistent names
- replaces local/legacy file references with relative `data/` paths
- updates several deprecated library calls and compatibility issues
- adds deterministic settings where appropriate
- adds documentation, dependency information, and dataset attribution

The original saved outputs are retained as evidence of the historical coursework run. Because compatibility updates were applied and some algorithms use random initialization, exact results can differ when the notebook is rerun with modern libraries.

## Methodological Limitations

This is educational coursework rather than a validated research benchmark. Important limitations include:

- question-difficulty labels are subjective and may differ between question authors and answer evaluators
- medium and hard labels are merged into one class for binary classification
- the project uses a single train/test split rather than cross-validation
- there is no independent baseline, hyperparameter-search protocol, or external test set
- the XGBoost configuration is highly complex for the dataset size and may overfit
- LDA is applied to TF-IDF features, although count-based features are more conventional for probabilistic topic models
- accuracy values should not be interpreted as generalizable performance claims
- the Universal Sentence Encoder model must be downloaded externally

The repository preserves these decisions transparently as part of the author's learning progression.
