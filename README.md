# Table of Contents
- [Table of Contents](#table-of-contents)
- [1. Methodology](#1-methodology)
  - [1.1. Grayscale](#11-grayscale)
- [2. Result](#2-result)

# 1. Methodology

 
## 1.1. Grayscale

We implemented 5 different masks for greyscale:

- The first method consists in calculate the average rgb of each pixel an set those (rgb) to the calculated average.
- The second method is Luma 601 which uses this function in the average calculation:

![alt text](https://wikimedia.org/api/rest_v1/media/math/render/svg/7abe41a6e7496f32b50c658ff646cc51e8495366 "Luma 601")
- The third method is Luma 709 which uses this function in the average calculation:

![alt text](https://wikimedia.org/api/rest_v1/media/math/render/svg/e26a409021fcb5ae8429adc475a7f426f83e7f7a "Luma 709")

- The fourth method is Luma 240 which uses this function in the average calculation:

![alt text](https://wikimedia.org/api/rest_v1/media/math/render/svg/2714d2c89f12a32700f6cf88e318d887a85b9e20 "Luma 240")

- The fifth method is Luma 2020 which uses this function in the average calculation:

![alt text](https://i.ibb.co/k12MHyT/Code-Cogs-Eqn.png "Luma 2020")

# 2. Result

![gif-can-grayscale](assets/GrayScale.gif)
  

