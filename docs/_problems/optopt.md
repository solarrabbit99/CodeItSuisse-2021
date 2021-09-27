---
layout: post
title: "Options Optimisation"
usemathjax: true
---

Suppose you are a trader and you get a deal with a bank, which is willing to buy from or sell to you a type of options over the counter. You will do the trade now, and will evaluate the Profit and Loss (PnL) in one month’s time and settle the payment. There will be no further trading throughout the one month.

There are two types of options, ‘call’ and ‘put’. Both of which the PnL depends on an underlying (say a particular stock) but they have different exposure. When trading options, the buyer of an option needs to pay a premium to the seller. However, the buyer gets a right to buy (if it’s a call option) or to sell (if it’s a put option) 1 quantity of the underlying from the seller at a specific price, named the strike price, after one month. The buyer may not exercise the right if it is not beneficial to them, but if the buyer does exercise the right, the seller must abide.

In general, the buyer of a call option profits when the underlying increases in value, while that of a put option profits when the underlying decreases in value. Suppose the stock price of 0005.HK (HSBC) is HKD 40, and if you believe it will increase in value to HKD 50 in a month, you can buy a call option from the bank, say with a premium of HKD 1 and at the strike price of HKD 45. Then, in a month’s time, you will need to pay the premium but you can buy one 0005.HK at HKD 45 and immediately sell it to the market at HKD 50. Overall, you can earn HKD 4 per option traded in this example. On the other hand in the same example, you can also sell a put option with say a premium of HKD 1 and at the strike price of HKD 40. In that case, in a month’s time, you earn the premium of HKD 1, and since the underlying price is HKD 50, the bank would not exercise the right to sell you the same underlying at a cheaper price.

Now in this problem, the bank is willing to trade with you in total at most 100 quantity of some mm options from a list. You may for example buy 50 of one call option and sell 50 of another put option, or all in one call option. All options in the list are derived from the same underlying, and the information is visible to you in the following format.

```json
{
  "options": [
    {
      "strike": 40,
      "premium": 1,
      "type": "put" // can only be 'put' or 'call'
    },
    {
      "strike": 40,
      "premium": 1,
      "type": "call"
    }
    // ... other options
  ]
  // ... other information
}
```

You did some data analytics and you have a view of the market in the next one month, and you expressed it in a mixed truncated Gaussian model. You can find more information about truncated Gaussians [here](https://en.wikipedia.org/wiki/Truncated_normal_distribution), and by ‘mixed’, it means that the model you have is in the form below:

$$f(x;\vec{w},\vec{\mu},\vec{\sigma},\vec{a},\vec{b})=\frac{1}{Z}\sum_{i=1}^nw_if_i(x;\mu_i,\sigma_i,a_i,b_i),$$

where $$f_i$$ and $$w_i$$ are the probability density function and the weight of the $$i$$-th truncated Gaussian, and

$$Z=\int_{\mathbb{R}}\sum_{i=1}^nw_if_i(x;\mu_i,\sigma_i,a_i,b_i)\,\mathrm{d}x$$

is the normalisation constant to make $$f$$ a probability density function. This information is visible to you in the following format.

```json
{
  "view": [
    {
      "mean": 40, // mu
      "var": 10, // sigma^2
      "min": 35, // a
      "max": 45, // b
      "weight": 1 // w
    },
    {
      "mean": 41,
      "var": 2,
      "min": 39,
      "max": 43,
      "weight": 2
    }
    // ... other truncated Gaussians
  ]
  // ... other information
}
```

Find the optimal porfolio, i.e. find out how many of each options to trade for your expected profit to be maximized, assuming you start and end with a flat position. Notice you can only trade at most 100 options, and you cannot trade a fractional amount of an option. Expose a POST endpoint `/optopt` that accepts a JSON object defined as above, and return the information in the format below:

```json
[
  30, // buy 30 of first option
  -20, // sell 20 of second option
  50, // buy 50 of third option
  0 // trade none of fourth option
]
```

where the ordering follows the one given in `options`.

Note: The options described is simplified from the one in real markets for the purpose of this problem.

## Constraints

- \\(1\le m,n\le 7000\\)
- \\(1\le \text{strike},\text{premium}\le 1000\\)
- \\(1\le w_i, \sigma_i \le 1000\\)
- \\(0< a_i, b_i, \mu_i < 1000\\)

## Subtasks

### Subtask 1 (5%)

- \\(m=1\\)
- \\(n=1\\)

### Subtask 2 (10%)

- \\(1<m\le 10\\)
- \\(n=1\\)

### Subtask 3 (20%)

- \\(1\le m\le 100\\)
- \\(1\le n\le 100\\)

### Subtask 4 (65%)

- \\(1\le m\le 7000\\)
- \\(1\le n\le 7000\\)
