# Question Difficulty Analysis with NLP and Machine Learning

## Overview

This repository contains an individual higher-grade project completed for the **Data Science** course during the Bachelor's degree.

The project analyzes a Wikipedia-based question-and-answer dataset and explores whether question difficulty can be modeled from question text. It combines data preprocessing, exploratory data analysis, natural-language processing, unsupervised learning, and supervised classification in a single Jupyter/Google Colab workflow.

## Project Workflow

The notebook covers:

- loading and combining three question-and-answer datasets
- handling missing values, malformed labels, and duplicate questions
- ordinal encoding of difficulty categories
- exploratory analysis of question and answer difficulty distributions
- TF-IDF text vectorization
- Latent Dirichlet Allocation topic modeling
- K-means question clustering
- word-cloud visualization of topics and clusters
- binary question-difficulty classification
- logistic regression with TF-IDF features
- Universal Sentence Encoder embeddings
- a Keras neural-network classifier
- XGBoost classification

## Dataset

The project uses the **CMU Question/Answer Dataset**, containing manually written factual questions and answers derived from Wikipedia articles. The included S08, S09, and S10 tables provide article titles, questions, answers, difficulty labels, and source-file identifiers.

After the original preprocessing steps, the coursework analysis retained approximately **2,203 unique answered questions** from **3,998 source rows**.

See [`DATASET.md`](DATASET.md) for attribution, citation, and licensing information.

## Original Coursework Results

The saved notebook outputs from the original 2022 run report approximately:

- **54% test accuracy** for TF-IDF with logistic regression
- **80% test accuracy** for Universal Sentence Encoder embeddings with a Keras neural network
- **82% test accuracy** for Universal Sentence Encoder embeddings with XGBoost

These values are preserved as historical coursework results rather than presented as benchmark-quality findings.

## Technologies Used

- Python
- Jupyter Notebook / Google Colab
- pandas and NumPy
- Matplotlib and WordCloud
- spaCy
- scikit-learn
- TensorFlow and Keras
- TensorFlow Hub / Universal Sentence Encoder
- XGBoost

## Repository Structure

```text
question-difficulty-nlp-analysis/
├── README.md
├── NOTICE.md
├── DATASET.md
├── requirements.txt
├── .gitignore
├── question-difficulty-nlp-analysis.ipynb
└── data/
    ├── s08_question_answer_pairs.tsv
    ├── s09_question_answer_pairs.tsv
    └── s10_question_answer_pairs.tsv
```

## Running the Notebook

The easiest option is to open the notebook in Google Colab from the project root so that the relative `data/` paths remain valid.

For local execution:

```bash
python -m venv .venv
```

Activate the environment and install the dependencies:

```bash
pip install -r requirements.txt
```

The Universal Sentence Encoder section downloads a model from TensorFlow Hub on its first run and therefore requires internet access.

## Academic Context

- **Course:** Data Science
- **Project type:** Individual higher-grade project
- **Programme:** Bachelor's degree
- **Author:** Ariton Verush
- **Original completion:** 2022

## Status

Completed academic project, cleaned and documented for inclusion in a public engineering and data-science portfolio.
