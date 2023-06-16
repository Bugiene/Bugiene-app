
# Bugiene Machine Learning Model

This repository contains the code for a machine learning model that performs implied computer vision tasks using a Convolutional Neural Network (CNN). The machine learning model was developed to classify fruits into rotten or fresh categories using computer vision and a convolutional neural network (CNN). The model was built using the TensorFlow Keras library. In this case, the CNN model was trained on a dataset of images of fruits, such as: apples, avocados, bananas, grapes, guavas, oranges. The dataset included images of both rotten and fresh fruits. The CNN was able to learn to distinguish between the two types of fruit based on the features it extracted from the images.The output from our model is prediction result "Fresh" or "Rotten" and the accuracy.

## Table of Contents

- [Dataset](#dataset)
- [Model Architecture](#model-architecture)
- [Requirements](#requirements)
- [Usage](#usage)
- [Results](#results)
- [Contributor Acknowledgment](#contributor-acknowledgment)  

## Dataset

The model is trained on a custom dataset consisting of labeled images. The dataset can be obtained from https://drive.google.com/file/d/1TIVGExDfYsShoewKA-32SJ7w-vtqdspe/view?usp=drive_link
 

## Model Architecture

The CNN model architecture used for this project is as follows:

pre_trained_model = VGG16(input_shape=(150,150,3), 
													include_top=False)
for layer in pre_trained_model.layers: 
	layer.trainable = False
x = layers.Dense(1024, activation='relu')(x)
x = layers.Dropout(0.2)(x)
x = layers.Dense(1, activation='sigmoid')(x)


## Requirements

To run the code in this repository, you will need the following dependencies:

- Python [3.10.10]

- TensorFlow [2.12.0]


## Usage
  
1. Clone this repository to your local machine.

2. Install the required dependencies by running `pip install -r requirements.txt`.

3. Clone this https://github.com/Bugiene/Bugiene-app/blob/master/machine-learning/bugiene_model.ipynb to your local machine.

4. Run the main script using `python main.py`.


## Results

Found 4004 images belonging to 2 classes.
4004/4004 [==============================] - 41s 10ms/step - loss: 0.1405 - accuracy: 0.9451
accuracy test:  0.9450549483299255
loss test:  0.14052222669124603

And for the result test you can check this https://github.com/Bugiene/Bugiene-app/tree/master/machine-learning/result-test


## Contributor Acknowledgment

We would like to acknowledge the following contributors for their valuable contributions to this project:
- Deni Irawan (GitHub: Densu341)
- Sandro Sinaga (GitHub: SandroSinaga24)
- Laila Nur Anggamurti (GitHub: jejukyul)


## Contact

For any questions or inquiries, please contact the contributor mentioned above. Thank you.
