NUM_SECURITY = 1;
NUM_SENSOR = 25;
NUM_INVADER = 2;

NUM_ROW_I = NUM_SECURITY * NUM_SENSOR * (NUM_INVADER);
NUM_ROW_J = NUM_SECURITY * NUM_SENSOR;

% Maxize this function
f =  zeros(1, NUM_ROW_I+NUM_ROW_J);

tmp_D_S3 = [1 20 100 40 60 70 80 90 100 100 100 100 100 100 100 10 30 100 50 60 80 100 110 100 130];
tmp_D = [tmp_D_S3];

tmp_P_I1 = [5	0	0	0	0	0	5	0	0	0	0	0	0	0	0	5	5	0	3	5	0	5	3	0	3];
tmp_P_I2 = [5	0	0	0	0	0	5	0	0	0	0	0	0	0	0	5	5	0	3	5	0	5	3	0	3];
tmp_P = [tmp_P_I1 tmp_P_I2];

tmp_X = zeros(1, NUM_ROW_I);

for j = 1:NUM_INVADER
    for k = 1:NUM_SECURITY
        for l = 1:NUM_SENSOR
            tmp_X((j - 1) * NUM_ROW_J + (k - 1) * NUM_SENSOR + l) = - tmp_P((j - 1) * NUM_SENSOR + l) / (10* tmp_D((k - 1) * NUM_SENSOR + l));
        end
    end
end

tmp_Y = zeros(1, NUM_ROW_J);

f = [tmp_X tmp_Y];

Aeq = [];
beq = [];

% Constraint condition 2
for i = 1:NUM_SECURITY * NUM_SENSOR
    tmp_X = zeros(1, NUM_ROW_I);
    tmp_Y = zeros(1, NUM_ROW_J);
    
    tmp_X(i) = 1;
    tmp_X(i + NUM_SECURITY * NUM_SENSOR) = -1;
    
    Aeq = [Aeq; [tmp_X tmp_Y]];
    beq = [beq; 0];
end

% Constraint condition 3-2
for i = 1:NUM_SECURITY
    tmp_X = zeros(1, NUM_ROW_I);
    tmp_Y = zeros(1, NUM_ROW_J);
    
    start = (i - 1) * NUM_SENSOR;
    finish = i * NUM_SENSOR;
    for k = start + 1:finish
        tmp_Y(k) = 1;
    end
    
    Aeq = [Aeq; [tmp_X tmp_Y]];
    beq = [beq; 1];
end

% Aineq * X <= bineq
Aineq = [];
bineq = [];

% Constraint condition 1
for i = 1:NUM_INVADER
    tmp_X = zeros(1, NUM_ROW_I);
    tmp_Y = zeros(1, NUM_ROW_J);
    
    start = (i - 1) * NUM_SECURITY * NUM_SENSOR;
    finish = i * NUM_SECURITY * NUM_SENSOR;
    for j = start + 1:finish
        k = rem (j, NUM_SENSOR);
        if (k == 0)
            k = NUM_SENSOR;
        end
        tmp_X(1, j) = -1 * tmp_P((i - 1) * NUM_SENSOR + k);
    end
    Aineq = [Aineq; [tmp_X tmp_Y]];
    bineq = [bineq; -1];
end

% Constraint condition 3-1
for i = 1:NUM_INVADER
    for j = 1: NUM_ROW_J
        tmp_X = zeros(1, NUM_ROW_I);
        tmp_Y = zeros(1, NUM_ROW_J);
    
        tmp_X(1, (i - 1) * NUM_ROW_J + j) = 1;
        tmp_Y(1, j) = -1;
    
        Aineq = [Aineq; [tmp_X tmp_Y]];
        bineq = [bineq; 0];
    end
end

% bintprog
x = bintprog (f ,Aineq ,bineq ,Aeq, beq)

% print result
fprintf('>>>>>>>>>>>>>>>>>>>>>>> RESULT <<<<<<<<<<<<<<<<<<<<<<<<<<<\n');
for i = NUM_ROW_I + 1:NUM_ROW_I + NUM_ROW_J
    if (x(i, 1) == 1)
        SECURITY = floor ((i - NUM_ROW_I - 1) / NUM_SENSOR) + 1;
        SENSOR = rem (i - NUM_ROW_I, NUM_SENSOR);
        if (SENSOR == 0) 
            SENSOR = NUM_SENSOR;
        end
        VAL = rem((i - NUM_ROW_I),  NUM_SENSOR);
        if (VAL == 0) 
            VAL = NUM_SENSOR;
        end
        for j = 1:NUM_INVADER
            if (tmp_P((j - 1) * NUM_SENSOR + VAL) == 1)
                INVADER = j;             
                fprintf('SECURITY( %d ) MOVES TO SENSOR( %d ) TO DETECT INVADER( %d )\n', SECURITY, SENSOR, INVADER);
     %           fprintf('SECURITY( %d ) MOVES TO SENSOR( %d )\n', SECURITY, SENSOR);
           end
        end
    end
end

