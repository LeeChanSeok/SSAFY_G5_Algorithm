def solution(alp, cop, problems):
    max_alp, max_cop = 0, 0
    for i in range(len(problems)):
        max_alp = max(problems[i][0], max_alp)
        max_cop = max(problems[i][1], max_cop)
    
    # dp 목적지 설정
    min_alp = min(max_alp, alp)
    min_cop = min(max_cop, cop)
    
    dp = [[float('inf')]*(max_cop+1) for _ in range(max_alp + 1)]
    dp[min_alp][min_cop] = 0
    
    for al in range(min_alp, max_alp + 1):
        for co in range(min_cop, max_cop + 1):
            # dp[al+1][co] = dp[al][co] (al까지 도달하는데 최소 시간)        
            if al + 1 <= max_alp:
                dp[al + 1][co] = min(dp[al + 1][co], dp[al][co] + 1)
            # dp[al][co+1] = dp[al][co] (co까지 도달하는데 최소 시간)
            if co + 1 <= max_cop:
                dp[al][co + 1] = min(dp[al][co + 1], dp[al][co] + 1)
            
            # 현재 al, co가 problems의 alp_req, cop_req의 이상이면(문제가 요구하는 수준 이상)이면 dp값 갱신
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if al >= alp_req and co >= cop_req:
                    next_alp = min(max_alp, al + alp_rwd)
                    next_cop = min(max_cop, co + cop_rwd)
                    dp[next_alp][next_cop] = min(dp[next_alp][next_cop], dp[al][co] + cost)
        
    return dp[-1][-1]
