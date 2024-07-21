
def gale_shapley(boys_pref_list, girls_pref_list):
    girl_boy = [None, None, None]
    for boy, boy_preferred_girl in enumerate(boys_pref_list):
        if girl_boy[boy_preferred_girl] == None:
            girl_boy[boy_preferred_girl] = boy
        else:
            boy_index = -1
            girl_cur_match = girl_boy[boy_preferred_girl]
            # get this boy's rank in girl's list
            for i , girl_pref in enumerate(girls_pref_list[boy_preferred_girl]):
                if girl_pref == boy:
                    boy_index = i
            # the girl prefer the boy better
            if boy_index < girl_cur_match:
                girl_boy[boy_preferred_girl] = boy_index

    return girl_boy

res = gale_shapley([0,1,0], [[1,0,2], [0,1,2], [0,1,2]])
print(res)
