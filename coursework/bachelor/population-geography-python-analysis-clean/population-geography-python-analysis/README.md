# Population Geography and Demographic Analysis with Python

## Overview

This repository contains an interdisciplinary Bachelor coursework project completed through a collaboration between the Faculty of Computer Science and Engineering and the Institute of Geography at Ss. Cyril and Methodius University in Skopje.

The project combines population geography with introductory Python-based data analysis. It studies historical demographic indicators for the Republic of Macedonia through a written report, structured CSV datasets, statistical summaries, visualizations, and exploratory regression experiments in Google Colab.

The coursework and report are written primarily in Macedonian.

## Academic Context

- **Courses:** Географија на население (Population Geography) and Население на Република Македонија (Population of the Republic of Macedonia)
- **Institution:** Ss. Cyril and Methodius University in Skopje
- **Collaborating units:** Faculty of Computer Science and Engineering and Institute of Geography, Faculty of Natural Sciences and Mathematics
- **Student:** Ariton Verush
- **Mentor:** проф. д-р Мирјанка Маџевиќ
- **Assistant:** доцент д-р Марија Љакоска
- **Year:** 2022

## Project Scope

The project examines:

- historical population development and migration
- annual birth rates
- mortality and natural population increase
- age structure
- ethnic structure
- urban and rural population distribution
- visualization of demographic change
- introductory predictive and regression-based experimentation

## Python Analysis

The Jupyter notebook uses:

- Python
- pandas
- NumPy
- Matplotlib
- seaborn
- scikit-learn
- Google Colab / Jupyter Notebook

The notebook loads six demographic datasets, produces tables and charts, calculates descriptive statistics, and explores introductory regression techniques.

## Repository Structure

```text
population-geography-python-analysis/
├── README.md
├── NOTICE.md
├── requirements.txt
├── .gitignore
├── population-demographics-analysis.ipynb
├── report/
│   └── population-geography-project-public.pdf
└── data/
    ├── annual_birth_rate.csv
    ├── natural_increase.csv
    ├── natural_increase_by_ethnicity.csv
    ├── age_structure.csv
    ├── ethnic_structure.csv
    └── urban_rural_population.csv
```

## How to Run

Install the dependencies:

```bash
pip install -r requirements.txt
```

Open `population-demographics-analysis.ipynb` in Jupyter Notebook, JupyterLab, or Google Colab.

The notebook expects the `data/` directory to remain beside the notebook in the repository root.


## Related Coursework

The written report references an introductory Python and Google Colab educational video created for the separate **Media and Communications** coursework project. That material is preserved in its own Bachelor portfolio folder and is not duplicated here.

## Data and Methodology Note

This repository preserves a historical coursework project rather than presenting a current official demographic analysis.

The datasets were transcribed or converted from course materials and referenced statistical sources. Some source tables contain formatting, transcription, or consistency issues. The regression experiments were introductory and should not be interpreted as authoritative demographic forecasts.

The large course textbook and redundant PDF conversions of individual tables are intentionally excluded from the public portfolio version.

## Status

Completed interdisciplinary Bachelor coursework project.

This public version preserves the report, executable analysis notebook, and supporting datasets while removing the student identification number and excluding unrelated or redundant course materials.
