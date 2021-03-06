﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Comeeting.Domain.Interfaces
{
    public interface ILivefeedMessageRepository
    {
        Task<List<LivefeedMessage>> GetLivefeedAsync();
        void AddLivefeedMessage(LivefeedMessage livefeedMessage);
    }
}
