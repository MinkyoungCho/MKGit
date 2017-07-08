import tensorflow as tf
import numpy as np
import csv

activity_list = ['Door_opened', 'Door_closed', 'Screen_down', 'Screen_up', 'Window_opened', 'Window_closed', 'Stand_up', 'Sit_down', 'Airconditioner_on', 'Airconditioner_off', 'Desk_cleaned', 'Roll_screen_up', 'Roll_screen_down', 'Entrance', 'Exit', 'Projector_on', 'Projector_off', 'Light_on', 'Light_off', 'White-board_writing', 'White-board_erasing', 'Humidifier_on', 'Humidifier_off', 'Connect_a_laptop', 'Disconnect_a_laptop']

activity_dic = {w:i for i,w in enumerate(activity_list)}
x_sequence = [] 
n_steps = 60 
n_input = 24

#Set training & testing length
training_len = 6344  
total_len = 7049 
training_seq_num = 438
total_seq_num = 488
display_steps = 40

with open('PublicData_people.txt') as f:
    reader = csv.reader(f, delimiter=" ")
    for row in reader:
        row_act = row
        row_people = reader.next()

        for i, activity in enumerate(row_act):
            if i == len(row_act) - 1:
                break
            temp_data = np.zeros(n_input)
            temp_data[int(activity)-1] = 1
            temp_data[n_input-1] = row_people[i]
            x_sequence.append(temp_data)
x_sequence = np.array(x_sequence)

f = np.loadtxt('0714_pb_seqLen.txt', dtype=int, delimiter=" ")
y_sequence = [] 

for i in range(total_seq_num):
   
   for j in range(f[i]):
       if j == 0:
        temp_data = [3, 0]
       else:
            temp_data = [0, 1]
       y_sequence.append(temp_data)
y_sequence = np.array(y_sequence)
print len(y_sequence)


#Configuration
rnn_size = 2 
batch_size = 1
learning_rate = 0.001
training_iter = 50

#tf Graph input
x = tf.placeholder(tf.float32, [batch_size, n_steps, n_input])
y = tf.placeholder(tf.float32, [n_steps, rnn_size])

#Define weights
weights = tf.Variable(tf.random_uniform([n_steps, batch_size], -1.0, 1.0))

#Form RNN model
def RNN(x):
    x = tf.reshape(x, [-1, n_input])
    x = tf.split(0, n_steps, x)
    
    rnn_cell = tf.nn.rnn_cell.BasicLSTMCell(rnn_size)
    state = tf.zeros([batch_size, rnn_cell.state_size])
    outputs, state = tf.nn.rnn(rnn_cell, x, state)
    return outputs

outputs = RNN(x)

logits = tf.reshape(tf.concat(1,outputs), [-1, rnn_size]) # prediction
targets = tf.reshape(y, [-1, rnn_size])  # truth: y_data

#Define loss and optimizer
pred = tf.cast(tf.arg_max(logits, 1), tf.float32)
cost = tf.reduce_sum(tf.square(targets-logits)) / batch_size
train_op = tf.train.AdamOptimizer(learning_rate=learning_rate).minimize(cost)
 
#Evaluate model
targets = tf.cast(targets, tf.int64)
correct_pred = tf.equal(tf.arg_max(logits, 1), tf.arg_max(targets, 1))
accuracy = tf.reduce_mean(tf.cast(correct_pred, tf.float32))

#Print segment boundaries
def segmentation(result, index):
    max_result = np.argmax(result, axis=1)	
    print '[3] Segmentation Index:', 
    for i in range(n_steps):    
        if max_result[i] == 0: #[1, 0]: Segment point
            temp_result = index+i
            print '>>', temp_result, 
        else: 
            temp_result = ''
            

with tf.Session() as sess:
    tf.initialize_all_variables().run()
    
	#Training LSTM model
    for j in range(0, training_iter):
        
        step = 0

        for i in range(0, training_len-n_steps):
            x_batch = x_sequence[step:step+n_steps]
            y_batch = y_sequence[step:step+n_steps]

            x_batch = x_batch.reshape(batch_size, n_steps, n_input)
            y_batch = y_batch.reshape(n_steps, rnn_size)
            sess.run(train_op, feed_dict={x: x_batch, y: y_batch})
            
            if step % 100  == 0:
                result = sess.run(logits, feed_dict={x:x_batch, y:y_batch})	
                loss = sess.run(cost, feed_dict={x:x_batch, y:y_batch})
                accu = sess.run(accuracy, feed_dict={x:x_batch, y:y_batch})
                print '[Iter:', j, '& step:', step , ']' 
                print '[1] Result:', result 
                print '[1] Minibatch Loss: ', "{:.6f}".format(loss)
                print '[2] Accuracy: ', "{:.6f}".format(accu)
                segmentation(result, i)
                print ''

            step += 1
     
    print ">>> Optimization Finished"


    #Testing
    for step in range (training_len, (total_len-n_steps)):

       x_batch = x_sequence[step:step+n_steps]
       y_batch = y_sequence[step:step+n_steps]

       x_batch = x_batch.reshape(batch_size, n_steps, n_input)
       y_batch = y_batch.reshape(n_steps, rnn_size)
     
    
       if (step-training_len) % display_steps  == 0:
          result = sess.run(logits, feed_dict={x:x_batch, y:y_batch})	
          accu = sess.run(accuracy, feed_dict={x:x_batch, y:y_batch})
	  print ''
	  print '[1] Sequence number:', step
	  print '[2] Accuracy: ', "{:.6f}".format(accu)
	  segmentation(result, step)
	  print ''

    print ">>> Testing Finished"


